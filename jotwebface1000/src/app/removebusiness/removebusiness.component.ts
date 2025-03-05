import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Business } from '../models/business.model';
import { Displayinfo } from '../models/displayinfo.model';
import { Menuinfo } from '../models/Menuinfo.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-removebusiness',
  templateUrl: './removebusiness.component.html',
  styleUrl: './removebusiness.component.css'
})
export class RemovebusinessComponent {

  constructor(private httpClient: HttpClient,private router: Router) {}
  
  Businesslist?: Business[];

  displayname:any ;
  rolename : any;
  moduleindex : any;
  client : any;
  version : any;

  globalId : any;

  DisplayinfoOne?: Displayinfo;

  Menuinfolist?: Menuinfo[];

  ngOnInit(): void {
    this.globalId=localStorage.getItem("globalid");
    this.moduleindex="2400";
    this.loadMenuBar();
    this.retriveLoginUserInformations();
    this.retriveBusinessInformations();
  }

  handleChange(evt:any) {
      var target = evt.target;
      var businessid=target.value;
      
      if(confirm("Are you sure to remove the business? "+businessid)) {
        this.removeBusinessInformation(businessid);
      }
  }


  public retriveBusinessInformations() {
 
     this.httpClient.get<any>('http://localhost:8080/Jotwebserviceapi1000/business/fetchall').subscribe({
       next: data => {
           this.Businesslist=data.pocket;
       },
       error: error => {
           console.error('There was an error!', error);
       }
   })
 
   }


   public removeBusinessInformation(businessid:any) {
    
    var url="http://localhost:8080/Jotwebserviceapi1000/business/remove/"+businessid;
    this.httpClient.delete<any>(url).subscribe({
      next: data => {
          alert(data.message);
          this.retriveBusinessInformations();
      },
      error: error => {
          console.error('There was an error!', error);
      }
  })

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

}
