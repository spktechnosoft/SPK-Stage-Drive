import { Component } from '@angular/core';

@Component({
  selector: 'ngx-footer',
  styleUrls: ['./footer.component.scss'],
  template: `
    <span class="created-by">Created with ♥ by <b><a href="https://justbit.it" target="_blank">Justbit Srl</a></b> 2019</span>
  `,
})
export class FooterComponent {
}
