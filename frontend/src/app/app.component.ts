import {Component, OnInit} from '@angular/core';
import {HelloHttpService} from "./hello-http.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  ngOnInit(): void {
    this.getTitle();
  }

  title: string = 'now working';

  constructor(private helloHttpService: HelloHttpService){

  }

  getTitle(){
    this.helloHttpService.getServers()
      .subscribe(
        (hello: any) => {
          console.log(hello)
          this.title = hello.hello,
            // this.servers = response.data;
            (error) => console.log(error)
        }
      );
  }
}
