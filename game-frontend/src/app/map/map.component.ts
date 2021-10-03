import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  tiles = [
    ['tree', 'tree', 'tree', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
    ['tree', 'tree', 'empty', 'fountain', 'house', 'house', 'empty', 'empty', 'empty', 'empty'],
    ['tree', 'tree', 'empty', 'empty', 'house', 'empty', 'empty', 'empty', 'tree', 'empty'],
    ['tree', 'tree', 'empty', 'empty', 'empty', 'empty', 'empty', 'tree', 'tree', 'tree'],
    ['tree', 'empty', 'empty', 'wind-turbine', 'wind-turbine', 'wind-turbine', 'empty', 'tree', 'tree', 'tree'],
    ['empty', 'circus', 'empty', 'wind-turbine', 'water', 'water', 'water', 'empty', 'tree', 'tree'],
    ['empty', 'empty', 'water', 'water', 'water', 'water', 'water', 'water', 'tree', 'tree'],
    ['empty', 'water', 'water', 'water', 'water', 'water', 'water', 'water', 'tree', 'tree'],
    ['water', 'water', 'water', 'water', 'water', 'water', 'water', 'water', 'empty', 'tree'],
    ['water', 'water', 'water', 'water', 'water', 'water', 'water', 'water', 'empty', 'tree']
  ]

  constructor () { }

  ngOnInit (): void {
  }
}
