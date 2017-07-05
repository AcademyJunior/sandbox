import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {environment} from "../environments/environment";

@Injectable()
export class HelloHttpService {

  constructor(private http: Http){
  }

  getServers() {
    return this.http.get(environment.apiUrl).map(
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
