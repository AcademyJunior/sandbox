import { Component, OnInit } from '@angular/core';
import {HelloHttpService} from "../../hello-http.service";

@Component({
  selector: 'app-example-feature',
  templateUrl: './example-feature.component.html',
  styleUrls: ['./example-feature.component.css']
})
export class ExampleFeatureComponent implements OnInit {

  constructor(private helloHttp: HelloHttpService) { }

  ngOnInit() {
    console.log(this.helloHttp.getServers());
  }

}
