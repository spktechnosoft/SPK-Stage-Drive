<?php

/*
 * This file is part of the Symfony package.
 *
 * (c) Fabien Potencier <fabien@symfony.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace WPRemoteMediaExt\Symfony\Component\EventDispatcher\DependencyInjection;

use WPRemoteMediaExt\Symfony\Component\DependencyInjection\Argument\ClosureProxyArgument;
use WPRemoteMediaExt\Symfony\Component\DependencyInjection\ContainerBuilder;
use WPRemoteMediaExt\Symfony\Component\DependencyInjection\Compiler\CompilerPassInterface;
use WPRemoteMediaExt\Symfony\Component\DependencyInjection\Exception\InvalidArgumentException;
use WPRemoteMediaExt\Symfony\Component\EventDispatcher\EventDispatcher;
use WPRemoteMediaExt\Symfony\Component\EventDispatcher\EventSubscriberInterface;

/**
 * Compiler pass to register tagged services for an event dispatcher.
 */
class RegisterListenersPass implements CompilerPassInterface
{
    /**
     * @var string
     */
    protected $dispatcherService;

    /**
     * @var string
     */
    protected $listenerTag;

    /**
     * @var string
     */
    protected $subscriberTag;

    /**
     * Constructor.
     *
     * @param string $dispatcherService Service name of the event dispatcher in processed container
     * @param string $listenerTag       Tag name used for listener
     * @param string $subscriberTag     Tag name used for subscribers
     */
    public function __construct($dispatcherService = 'event_dispatcher', $listenerTag = 'kernel.event_listener', $subscriberTag = 'kernel.event_subscriber')
    {
        $this->dispatcherService = $dispatcherService;
        $this->listenerTag = $listenerTag;
        $this->subscriberTag = $subscriberTag;
    }

    public function process(ContainerBuilder $container)
    {
        if (!$container->hasDefinition($this->dispatcherService) && !$container->hasAlias($this->dispatcherService)) {
            return;
        }

        $definition = $container->findDefinition($this->dispatcherService);

        foreach ($container->findTaggedServiceIds($this->listenerTag) as $id => $events) {
            $def = $container->getDefinition($id);
            if ($def->isAbstract()) {
                throw new InvalidArgumentException(sprintf('The service "%s" must not be abstract as event listeners are lazy-loaded.', $id));
            }

            foreach ($events as $event) {
                $priority = isset($event['priority']) ? $event['priority'] : 0;

                if (!isset($event['event'])) {
                    throw new InvalidArgumentException(sprintf('Service "%s" must define the "event" attribute on "%s" tags.', $id, $this->listenerTag));
                }

                if (!isset($event['method'])) {
                    $event['method'] = 'on'.preg_replace_callback(array(
                        '/(?<=\b)[a-z]/i',
                        '/[^a-z0-9]/i',
                    ), function ($matches) { return strtoupper($matches[0]); }, $event['event']);
                    $event['method'] = preg_replace('/[^a-z0-9]/i', '', $event['method']);
                }

                $definition->addMethodCall('addListener', array($event['event'], new ClosureProxyArgument($id, $event['method']), $priority));
            }
        }

        $extractingDispatcher = new ExtractingEventDispatcher();

        foreach ($container->findTaggedServiceIds($this->subscriberTag) as $id => $attributes) {
            $def = $container->getDefinition($id);
            if ($def->isAbstract()) {
                throw new InvalidArgumentException(sprintf('The service "%s" must not be abstract as event subscribers are lazy-loaded.', $id));
            }

            // We must assume that the class value has been correctly filled, even if the service is created by a factory
            $class = $container->getParameterBag()->resolveValue($def->getClass());
            $interface = 'WPRemoteMediaExt\Symfony\Component\EventDispatcher\EventSubscriberInterface';

            if (!is_subclass_of($class, $interface)) {
                if (!class_exists($class, false)) {
                    throw new InvalidArgumentException(sprintf('Class "%s" used for service "%s" cannot be found.', $class, $id));
                }

                throw new InvalidArgumentException(sprintf('Service "%s" must implement interface "%s".', $id, $interface));
            }
            $container->addObjectResource($class);

            ExtractingEventDispatcher::$subscriber = $class;
            $extractingDispatcher->addSubscriber($extractingDispatcher);
            foreach ($extractingDispatcher->listeners as $args) {
                $args[1] = new ClosureProxyArgument($id, $args[1]);
                $definition->addMethodCall('addListener', $args);
            }
            $extractingDispatcher->listeners = array();
        }
    }
}

/**
 * @internal
 */
class ExtractingEventDispatcher extends EventDispatcher implements EventSubscriberInterface
{
    public $listeners = array();

    public static $subscriber;

    public function addListener($eventName, $listener, $priority = 0)
    {
        $this->listeners[] = array($eventName, $listener[1], $priority);
    }

    public static function getSubscribedEvents()
    {
        $callback = array(self::$subscriber, 'getSubscribedEvents');

        return $callback();
    }
}
