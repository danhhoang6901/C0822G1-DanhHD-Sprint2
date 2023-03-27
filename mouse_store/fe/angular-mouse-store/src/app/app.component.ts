import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-mouse-store';
  // tslint:disable-next-line:typedef
  onActivate($event: any) {
    // window.scroll(0,0);

    window.scroll({
      top: 700,
      behavior: 'smooth'
    });
  }
}
