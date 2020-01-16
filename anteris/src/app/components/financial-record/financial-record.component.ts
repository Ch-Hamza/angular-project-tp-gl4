import {Component, OnInit} from '@angular/core';
import {Record} from '../../models/record';
import {FinancialRecordsService} from '../../services/financialRecords.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {FileUploadService} from '../../services/fileUpload.service';
import {UserService} from '../../services/user.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-financial-record',
  templateUrl: './financial-record.component.html',
  styleUrls: ['./financial-record.component.css']
})
export class FinancialRecordComponent implements OnInit {

  fileToUpload: File = null;
  data: string;
  modelRecord: FormGroup;
  records: Record[];
  editMode = false;
  imageToShow: any;
  isImageLoading: boolean;

  selectedRecord: Record;
  dropdownListType = [
    'DEPOSIT',
    'WITHDRAW'
  ];

  constructor(private financialService: FinancialRecordsService,
              private formBuilder: FormBuilder,
              private toastr: ToastrService,
              private domSanitizer: DomSanitizer,
              private fileUploadService: FileUploadService) {
  }

  ngOnInit() {
    this.records = [];
    this.initForm();
    this.financialService.findAll().subscribe(
      (data) => this.records = data as Record[]
    );
  }
  createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener( "load", () => {
      this.imageToShow = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }
  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }
  initForm() {
    this.modelRecord = this.formBuilder.group({
      label: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      image: [''],
      type: []
    });
  }

  deleteRecord(id, i) {
    this.financialService.removeById(id).subscribe(
      () => this.records.splice(i, 1)
    );
  }

  showRecord(id, i) {
      this.fileUploadService.getImage(this.records[i].image).subscribe(imgData => this.data = imgData.replace('data:application/json', 'data:image/png') );
  }

  onSubmit() {
    if (this.modelRecord.invalid) {
      return;
    }

    const record = this.modelRecord.value;
    this.fileUploadService.postFile(this.fileToUpload).subscribe(data => {
      record.image = data;
      this.financialService.newRecord(record).subscribe(
        (da) => {
          this.records.push(da as Record);
          this.toastr.success(  'Record Inserted');
        }
      );
    }, error => {
      this.toastr.error(  'File Upload Error');
    });
  }

}
