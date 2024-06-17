import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { User } from '../state/user/user.model';

const url = 'http://localhost:8080/v1/customer';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private apiService: ApiService) {}

  login = (user: User): Observable<User> => {
    return this.apiService.get(url + '?email=' + user.email);
  };

  createAccount = (user: User): Observable<User> => {
    return this.apiService.post(url, user);
  };
}
