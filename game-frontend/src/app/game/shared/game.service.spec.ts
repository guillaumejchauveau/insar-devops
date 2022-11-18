import { TestBed } from '@angular/core/testing';
import { GameService, IMapModel } from './game.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InteractoModule } from 'interacto-angular';
import { RouterTestingModule } from '@angular/router/testing';
import { MapModel } from '../map/map.model';
import { NatureTile } from './tile.model';

describe('GameService', () => {
  let service: GameService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        InteractoModule,
        RouterTestingModule
      ]
    });

    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(GameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getMapNames works', async () => {
    const promise: Promise<string[]> = service.getMapNames();
    const testData: string[] = ['testname1', 'testname2', 'testname3'];

    const req = httpTestingController.expectOne('/api/maps');
    expect(req.request.method).toEqual('GET');
    req.flush(testData);
    httpTestingController.verify();

    const names: string[] = await promise;
    expect(names).toEqual(testData);
  });

  it('generateMap works', async () => {
    const promise: Promise<MapModel> = service.generateMap();
    const tiles = Array.from({length: 2}, () => NatureTile.grass);
    const testData: IMapModel = {
      name: 'test',
      width: 10,
      height: 10,
      startTiles: tiles
    };
    const resData: MapModel = new MapModel('test', 10, 10, tiles);
    const req = httpTestingController.expectOne('/api/maps', undefined);

    expect(req.request.method).toEqual('POST');
    req.flush(testData);
    httpTestingController.verify();

    const mapModel: MapModel = await promise;
    expect(mapModel).toEqual(resData);
  });

  it('getMapByName works', async () => {
    const promise: Promise<MapModel> = service.getMapByName('test');
    const testData: MapModel = new MapModel('test', 0, 0, []);

    const req = httpTestingController.expectOne('/api/maps/test');
    expect(req.request.method).toEqual('GET');
    req.flush(testData);
    httpTestingController.verify();

    const mapModel: MapModel = await promise;
    expect(mapModel).toEqual(testData);
  });
});
