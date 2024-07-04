import { TestBed } from '@angular/core/testing';

import { InterceptorsAuthService } from './interceptors-auth.service';

describe('InterceptorsAuthService', () => {
  let service: InterceptorsAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterceptorsAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
