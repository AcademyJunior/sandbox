import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {environment} from "../environments/environment";

@Injectable()
export class FeatureHttpService {

  constructor(private http: Http){
  }

  getFeatures() {
    return this.http.get(environment.apiUrl + "/feature").map(
      (response: Response) => {
        return response.json();
      }
    ).catch(
      (error: Response) => {
        return Observable.throw('Something went wrong');
      }
    );
  }
}
