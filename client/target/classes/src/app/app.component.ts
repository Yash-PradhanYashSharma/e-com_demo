import {Component} from '@angular/core';
import {NetworkService} from "./service/network.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  constructor(public networkService: NetworkService) {
    this.networkService.getParty().subscribe(response => {
      console.log('------constructor- call----------',response)
      });
  }

  ngOnInit() {
  }

  isAuthenticated: boolean;
  title = 'MuleSoft Integration Middleware Accelerator';

}
