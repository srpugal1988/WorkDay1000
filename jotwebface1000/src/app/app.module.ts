import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
// import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { CreatebusinessComponent } from './createbusiness/createbusiness.component';
import { ReadbusinessComponent } from './readbusiness/readbusiness.component';
import { ModifybusinessComponent } from './modifybusiness/modifybusiness.component';
import { RemovebusinessComponent } from './removebusiness/removebusiness.component';
import { ApplicationUserComponent } from './application-user/application-user.component';
import { ApplicationRoleComponent } from './application-role/application-role.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    CreatebusinessComponent,
    ReadbusinessComponent,
    ModifybusinessComponent,
    RemovebusinessComponent,
    ApplicationUserComponent,
    ApplicationRoleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch()) 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
