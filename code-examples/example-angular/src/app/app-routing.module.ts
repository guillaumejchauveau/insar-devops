import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ExComponent} from './ex/ex.component';
import {Ex2Component} from './ex2/ex2.component';

const routes: Routes = [
  { path: 'ex', component: ExComponent },
  { path: 'ex2', component: Ex2Component },
  { path: '',
    redirectTo: '/ex2',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
