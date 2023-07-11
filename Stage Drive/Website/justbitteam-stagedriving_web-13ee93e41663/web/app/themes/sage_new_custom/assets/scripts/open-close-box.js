(function($) {
	// define Function 
	var close, onLayout, show;

	var counter = 0,
	counter2 = 0,
	eleFound = false,
	boxActived, box, onLayoutTimeout;
	window.closeBox = function() {};
	window.openBox = function(element, container, masonryElement, itemTemplate, boxTemplate) {
		if (!itemTemplate) {
			console.error('itemTemplate undefined. ', itemTemplate);
			return;
		}
		if (!boxTemplate) {
			console.error('boxTemplate undefined. ', boxTemplate);
			return;
		}
		if (!masonryElement) {
			console.error('masonryElement undefined. ', masonryElement);
			return;
		}

		box = element.find('.box').clone();
		counter = 0;
		eleFound = false;
		onLayoutTimeout = null;

		close = function() {
			if (!boxActived) {
				return;
			}

			var isLastRow = false;
			if (boxActived.hasClass('last-row')) {
				boxActived.removeClass('slideInDown');
				boxActived.addClass('slideOutUp');
				isLastRow = true;
			} else {
				boxActived.hide();
				masonryElement.masonry('layout');
			}
			
			boxActived.html('');

			element.parent('div').find('.active').removeClass('active');
			boxActived = null;

			if (isLastRow) {
				isLastRow = false;
				onLayout();
			}
		};
		show = function() {
			boxActived.show();
			if (boxActived.hasClass('last-row')) {
				boxActived.removeClass('slideOutUp');
				boxActived.addClass('slideInDown');
			} else {
				masonryElement.masonry('layout');
			}
			
			element.addClass('active');
		};
		onLayout = function() {
			var containerChildren = container.children(),
			containerChildrenVisible = container.children(':visible');

			containerChildren.each(function(index) {
				if (!$(this).hasClass('box') && $(this).is(":visible")) {
					if (element[0] === $(this)[0]) {
						eleFound = true;
					}

					var classList = this.classList;
					var classFound = false;
					for (var k = 0; k < classList.length; k++) {
						var classNow = classList[k];
						if (typeof classNow.indexOf !== 'function') {
							console.error('Element: ', this, classNow);
						}
						if (classNow.indexOf('col-' + window.bootstrapDevice) !== -1) {
							var number = parseInt(classNow.replace('col-' + window.bootstrapDevice + '-', ''));
							counter += number;
							classFound = true;
							break;
						}
					}
					if (!classFound) {
						console.error('bootstrap class not found: ', window.bootstrapDevice);
					}

					if (counter === 12) {
						if (eleFound) {
							eleFound = false;
							// Get Box & Replace HTML
							
							boxActived = $(containerChildren[(index + 1)]);
							
							
							boxActived.html(box.html());
							
							// Add Close Event
							boxActived.find('a.box-close').on('click', function(e) {
								e.preventDefault();
								e.stopPropagation();
								close();
								return false;
							});

							show();
							counter = 0;
							return false;
						} else {
							counter = 0;
						}
					}
				}
			});
		};
		if (boxActived) {
			if (!boxActived.hasClass('last-row')) {
				// masonryElement.one('layoutComplete', onLayout);
			}
			close();

			onLayoutTimeout && clearTimeout(onLayoutTimeout);
			onLayoutTimeout = setTimeout(function() {
				onLayout();
			}, 0);
		} else {
			onLayout();
		}

		window.closeBox = close;
		$(window).on('resize', close);
	};
	window.completeBootstrapGrid = function(container, masonryElement, itemTemplate, boxTemplate) {
		var containerChildren = container.children('div:not(.box):not(.item-template):visible');
		counter2 = 0;
		containerChildren.each(function(index) {
			if (!$(this).hasClass('box') && !$(this).hasClass('item-template') && $(this).is(":visible")) {
				var classList = this.classList;
				for (var k = 0; k < classList.length; k++) {
					var classNow = classList[k];
					if (typeof classNow.indexOf !== 'function') {
						console.error('Element: ', classNow);
					}
					if (classNow.indexOf('col-' + window.bootstrapDevice) !== -1) {
						var number = parseInt(classNow.replace('col-' + window.bootstrapDevice + '-', ''));
						counter2 += number;
						break;
					}
				}


				var isLastRow = ((containerChildren.length-1) === index);
				if (counter2 > 0 && counter2 < 12 && isLastRow) {
					itemTemplate.show();

					var columnMissed = (12-counter2);
					itemTemplate.addClass('col-xs-' + columnMissed);
					itemTemplate.addClass('col-sm-' + columnMissed);
					itemTemplate.addClass('col-md-' + columnMissed);
					itemTemplate.addClass('col-lg-' + columnMissed);

					masonryElement.masonry('layout');
				} else if (counter2 === 12) {
					counter2 = 0;
				} else {
					itemTemplate.hide();
					masonryElement.masonry('layout');
				}
			}
		});
	};
})(jQuery); // Fully reference jQuery after this point.