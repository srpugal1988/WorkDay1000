import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component,Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Displayinfo } from '../models/displayinfo.model';
import { Menuinfo } from '../models/Menuinfo.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  displayname:any ;
  rolename : any;
  moduleindex : any;
  client : any;
  version : any;

  globalId : any;

  DisplayinfoOne?: Displayinfo;

  Menuinfolist?: Menuinfo[];

  constructor(private httpClient: HttpClient,private router: Router) {}
  

  ngOnInit(): void {
    
    this.globalId=localStorage.getItem("globalid");
    this.moduleindex="1000";
    this.loadMenuBar();
    this.retriveLoginUserInformations();
    //alert(this.userId);
  }


  
 loadMenuBar(): void{

        var url="http://localhost:8080/Jotwebserviceapi1000/menu?id="+this.globalId;
        this.httpClient.get<any>(url).subscribe({
          next: data => {

            this.Menuinfolist = data.pocket;
            this.myFunction(this.Menuinfolist);
              
          },
          error: error => {
              console.error('There was an error!', error);
          }
      })

  }

  myFunction(arr:any):void {

    var out = "";
    var i;
    for(i = 0; i < arr.length; i++) {
       var mid="M"+arr[i].id;
       var rights=arr[i].rights;
       
       
       if(rights=="1"){
            (<HTMLInputElement>document.getElementById(mid)).style.display="block";
       }else{
            (<HTMLInputElement>document.getElementById(mid)).style.display="none";
       }
       
    }
}

  retriveLoginUserInformations(): void {

    var url="http://localhost:8080/Jotwebserviceapi1000/auth/checkLoginUser?moduleindex="+this.moduleindex+"&id="+this.globalId;
    
    this.httpClient.get<any>(url).subscribe({
      next: data => {

        if(data.code=='100'){
              this.DisplayinfoOne = data.pocket;
              this.displayname = this.DisplayinfoOne?.displayname;
              this.rolename = this.DisplayinfoOne?.rolename;
              this.client = this.DisplayinfoOne?.client;
              this.version = this.DisplayinfoOne?.version;
        }
        else if(data.code=='99'){
              this.router.navigate(['jotwebface1000/login']);
              alert("Kindly login to proceed further!...");
        }
        else if(data.code=='98'){
              this.router.navigate(['jotwebface1000/login']);
              alert("You dont have access to this page! Kindly login");
        }
  
      },
      error: error => {
          console.error('There was an error!', error);
      }
  })


  }


  proceedlogout() : void {
    
      var thisurl="http://localhost:8080/Jotwebserviceapi1000/auth/logout?id="+this.globalId;
      this.httpClient.get<any>(thisurl).subscribe({
        next: data => {
            alert("Got response:");
            alert("Code:"+data.code);
            localStorage.clear();
            this.router.navigate(['jotwebface1000/login']);
        },
        error: error => {
             alert("error");
            console.error('There was an error!', error);
        }
    })
  

  }
 
}
