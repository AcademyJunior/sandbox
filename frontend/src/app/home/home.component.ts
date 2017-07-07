import {Component, OnInit} from "@angular/core";
import {FeatureHttpService} from "../feature-http.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private featureHttpService: FeatureHttpService) {
  }

  features = [];

  ngOnInit() {
    this.featureHttpService.getFeatures().subscribe(
      (features) => this.features = features
    );
  }
}


