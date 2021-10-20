import { TestBed } from '@angular/core/testing';

import { GameMapManagerService } from './game-map-manager.service';

describe('GameMapManagerService', () => {
  let service: GameMapManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameMapManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
