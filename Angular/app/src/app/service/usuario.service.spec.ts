import { TestBed } from '@angular/core/testing';

import { CajeroService } from './usuario.service';

describe('CajeroService', () => {
  let service: CajeroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CajeroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
