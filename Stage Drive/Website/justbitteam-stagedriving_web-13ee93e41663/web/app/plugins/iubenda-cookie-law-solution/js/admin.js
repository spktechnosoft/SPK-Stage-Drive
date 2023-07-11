( function ( $ ) {

	$( document ).ready( function () {

		// read more option
		$( '#iub_parse' ).change( function () {
			if ( $( this ).is( ':checked' ) ) {
				$( '#iub_parser_engine_container' ).slideDown( 'fast' );
			} else {
				$( '#iub_parser_engine_container' ).slideUp( 'fast' );
			}
		} );

		// move notices
		var errors = $( '.settings-error' ).detach();

		$( '.iubenda-link' ).after( errors );

		/**
		 * Help tabs.
		 */
		$( '.contextual-help-tabs a' ).off( 'click' ).click( function ( e ) {
			var link = $( this ),
				panel,
				panelParent;

			e.preventDefault();

			// don't do anything if the click is for the tab already showing.
			if ( link.is( '.active a' ) )
				return false;
			
			panelParent = link.closest( '.contextual-help-wrap' );

			// links
			$( panelParent ).find( '.contextual-help-tabs .active' ).removeClass( 'active' );
			link.parent( 'li' ).addClass( 'active' );

			panel = $( link.attr( 'href' ) );

			// panels
			$( panelParent ).find( '.help-tab-content' ).not( panel ).removeClass( 'active' ).hide();
			panel.addClass( 'active' ).show();
		} );

	} );

} )( jQuery );