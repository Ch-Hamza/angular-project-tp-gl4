import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FinancialRecordsService {
  api = 'http://localhost:8080/financial/';

  constructor(private http: HttpClient) { }
  findAll() {
    return this.http.get(this.api);
  }
  findById(id) {
    return this.http.get(this.api + id);
  }
  newRecord(record) {
    return this.http.post(this.api, record);
  }
  removeById(id) {
    return this.http.delete(this.api + id);
  }
}
