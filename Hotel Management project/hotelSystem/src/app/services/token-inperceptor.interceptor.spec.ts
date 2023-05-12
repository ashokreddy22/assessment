import { TestBed } from '@angular/core/testing';

import { TokenInperceptorInterceptor } from './token-inperceptor.interceptor';

describe('TokenInperceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      TokenInperceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: TokenInperceptorInterceptor = TestBed.inject(TokenInperceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
