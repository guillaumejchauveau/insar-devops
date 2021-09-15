import { Injectable } from '@angular/core';

export interface Data {
  name: string;
  color: string;
}

@Injectable({
  providedIn: 'root'
})
export class FooService {
  private serviceData: Data;

  constructor() {
    this.serviceData = {
      name: 'fooName',
      color: 'red'
    };
  }

  get data(): Data {
    return this.serviceData;
  }
}
