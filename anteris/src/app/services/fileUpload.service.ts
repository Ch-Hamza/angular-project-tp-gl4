import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {switchMap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  api = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  postFile(fileToUpload: File) {
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload);
    return this.http.post(this.api + 'upload-file', formData, {responseType: 'text'});
  }
  getImage(imageUrl: string): Observable<string> {
    return this.http.get(this.api + 'files/' + imageUrl, { responseType: 'blob' }).pipe(
      switchMap(response => this.readFile(response))
    );
  }
  private readFile(blob: Blob): Observable<string> {
    return Observable.create(obs => {
      const reader = new FileReader();

      reader.onerror = err => obs.error(err);
      reader.onabort = err => obs.error(err);
      reader.onload = () => obs.next(reader.result);
      reader.onloadend = () => obs.complete();

      return reader.readAsDataURL(blob);
    });
  }
}
