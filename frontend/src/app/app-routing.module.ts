import {NgModule} from "@angular/core";
import {Routes, RouterModule, PreloadAllModules} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {ExampleFeatureComponent} from "./features/example-feature/example-feature.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'example', component: ExampleFeatureComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
