import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplaysMenuComponent } from './replays-menu.component';

describe('ReplaysMenuComponent', () => {
  let component: ReplaysMenuComponent;
  let fixture: ComponentFixture<ReplaysMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReplaysMenuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReplaysMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
