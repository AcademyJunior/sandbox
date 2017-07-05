import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() {
  }

  description = 'Programmable devices have existed at least as far back as 1206 AD, when the automata of Al-Jazari' +
    ' were programmable, via pegs and cams, to play various rhythms and drum patterns';

  features = [
    {author: 'Ninja', name: 'example', description: this.description},
    {author: 'Orzech', name: 'example', description: this.description},
    {author: 'Ninja', name: 'example', description: this.description},
    {author: 'Orzech', name: 'example', description: this.description},
    {author: 'Ninja', name: 'example', description: this.description},
    {author: 'Orzech', name: 'example', description: this.description},
    {author: 'Maras', name: 'example', description: this.description}
  ];

  ngOnInit() {
  }

  getFeatures() {
    return this.features;
  }

}
