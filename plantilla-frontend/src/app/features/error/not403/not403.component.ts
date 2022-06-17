import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-not403',
  templateUrl: './not403.component.html',
  styleUrls: ['./not403.component.scss']
})
export class Not403Component implements OnInit {

  usuario: string;

  // npm install @auth0/angular-jwt --save
  constructor() { }

  ngOnInit() {
    const helper = new JwtHelperService();
    let token = sessionStorage.getItem(environment.TOKEN_NAME);
    const decodedToken = helper.decodeToken(token);
    this.usuario = decodedToken.user_name;
  }
}
