import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialRecordComponent } from './financial-record.component';

describe('FinancialRecordComponent', () => {
  let component: FinancialRecordComponent;
  let fixture: ComponentFixture<FinancialRecordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinancialRecordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinancialRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
