import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit{

  title = 'demoSecurityFronted';
  showNavbar=false;

  constructor(private router:Router){}

  ngOnInit(): void {
    this.router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        this.checkRoute();
      }
    })
  }

  checkRoute(){
    const currentRoute=this.router.url;
    this.showNavbar=currentRoute!=='/login';
  }
  
}
