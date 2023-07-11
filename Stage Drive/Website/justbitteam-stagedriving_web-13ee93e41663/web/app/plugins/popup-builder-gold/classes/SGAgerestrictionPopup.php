<?php
require_once(dirname(__FILE__).'/SGPopup.php');

class SGAgerestrictionPopup extends SGPopup {
	private $content;
	private $yesBuuton;
	private $noBuuton;
	private $restrictionUrl;

	function __construct() {
		wp_register_script('restictionCustomJs', SG_APP_POPUP_URL . '/javascript/sg_ageRestriction.js', array(), SG_POPUP_VERSION);
		wp_enqueue_script('restictionCustomJs');
	}
	public function setContent($content) {
		$this->content = $content;
	}
	public function getContent() {
		return $this->content;
	}
	public function setYesButton($label) {
		$this->yesBuuton = $label;
	}
	public function getYesButton() {
		return $this->yesBuuton;
	}
	public function setNoButton($label) {
		$this->noBuuton = $label;
	}
	public function getNoButton() {
		return $this->noBuuton;
	}
	public function setRestrictionUrl($restrictionUrl) {
		$this->restrictionUrl = $restrictionUrl;
	}
	public function getRestrictionUrl() {
		return $this->restrictionUrl;
	}
	public static function create($data, $obj = null) {

		$options = json_decode($data['options']);
		$yesButtonLabel = $options->yesButtonLabel;
		$noButtonLabel = $options->noButtonLabel;
		$sgRestrictionUrl = $options->restrictionUrl;
		$sgRestriction = $data['ageRestriction'];

		$obj = new self();

		$obj->setYesButton($yesButtonLabel);
		$obj->setNoButton($noButtonLabel);
		$obj->setRestrictionUrl($sgRestrictionUrl);
		$obj->setContent($sgRestriction);

		return parent::create($data, $obj);
	}
	public function save($data = array()) {

		$editMode = $this->getId()?true:false;

		$res = parent::save($data);
		if ($res===false) return false;

		$sgAgerestriction = stripslashes($this->getContent());
		$sgYesBuuton = $this->getYesButton();
		$sgNoBuuton = $this->getNoButton();
		$sgRestrictionUrl = $this->getRestrictionUrl();

		global $wpdb;
		if ($editMode) {
			$sql = $wpdb->prepare("UPDATE ". $wpdb->prefix ."sg_age_restriction_popup SET content=%s,yesButton=%s,noButton=%s,url=%s WHERE id=%d",$sgAgerestriction,$sgYesBuuton,$sgNoBuuton,$sgRestrictionUrl,$this->getId());
			$res = $wpdb->query($sql);
		}
		else {
			$sql = $wpdb->prepare( "INSERT INTO ". $wpdb->prefix ."sg_age_restriction_popup (id,content,yesButton,noButton,url) VALUES (%d,%s,%s,%s,%s)",$this->getId(),$sgAgerestriction,$sgYesBuuton,$sgNoBuuton,$sgRestrictionUrl);
			$res = $wpdb->query($sql);
		}
		return $res;
	}

	protected function setCustomOptions($id) {
		global $wpdb;
		$st = $wpdb->prepare("SELECT * FROM ". $wpdb->prefix ."sg_age_restriction_popup WHERE id = %d",$id);
		$arr = $wpdb->get_row($st,ARRAY_A);
		$this->setContent($arr['content']);
		$this->setYesButton($arr['yesButton']);
		$this->setNoButton($arr['noButton']);
		$this->setRestrictionUrl($arr['url']);
	}

	public function getRemoveOptions()
	{
		return array('showOnlyOnce' => 1, 'repetitivePopup' => 1, 'contentClick' => 1);
	}

	protected function getExtraRenderOptions() {
		$options = $this->getOptions();
		$optionsArray = json_decode($options,true);
		$restrictionAction = @$optionsArray['restrictionAction'];
		$restrictionUrl = $optionsArray['restrictionUrl'];
		$pushToBottom = $optionsArray['pushToBottom'];
		$expirationTime = $optionsArray['sgRestrictionExpirationTime'];
		$restrictionCookeSavingLevel = $optionsArray['restrictionCookeSavingLevel'];
		$content = $this->getContent();

		$popupId = (int)$this->getId();
		$content.= "<div class=\"buttons-wrapper js-sg-push-on-bottom\" ><button id='sgYesButton' class='sg-restriction-button' type='button' > ".$this->getYesButton()."</button><button id='sgNoButton'  class='sg-restriction-button' type='button' style='margin-left: 5px;' > ".$this->getNoButton()."</button></div>";
		$ageRestrcitionParams = array(
			"id" => $this->getId(),
			"restrictionUrl" => $restrictionUrl,
			"pushToBottom" => $pushToBottom,
			"expirationTime" => $expirationTime,
			"restrictionCookeSavingLevel" => $restrictionCookeSavingLevel
		);
		wp_register_script('restictionCustomJs', SG_APP_POPUP_URL . '/javascript/sg_ageRestriction.js');
		wp_localize_script('restictionCustomJs', 'SgAgeRestrcitionParams'.$popupId, $ageRestrcitionParams);
		wp_enqueue_script('restictionCustomJs');

		
		$content.= "<style>
			.buttons-wrapper {
				text-align: center;
			}
			#sgcboxLoadedContent .buttons-wrapper button {
				padding: 12px !important;
				line-height: 0.4;
				 margin-bottom: 4px;
			}
			.sg-restriction-button {
				cursor: pointer !important;
			}
			.sg-hide-overflow {
				overflow: hidden;
			}

			.sg-push-to-bottom {
				position: absolute !important;
				bottom: 2px !important;
				left: 0 !important;
				right: 0 !important
			}
		</style>";
		$hasShortcode = $this->hasPopupContentShortcode($content);

		if($hasShortcode) {
			
			$content =  $this->improveContent($content);
		}
		$this->sgAddPopupContentToFooter($content, $popupId);
		$content = trim($content);

		return array('html'=> $content,'contentClick'=>'','overlayClose'=>'','escKey'=>'','closeButton'=>'','repeatPopup'=>'on');
	}

	public  function render() {
		return parent::render();
	}
}
