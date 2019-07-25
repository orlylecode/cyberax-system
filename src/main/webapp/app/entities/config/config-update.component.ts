import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IConfig, Config } from 'app/shared/model/config.model';
import { ConfigService } from './config.service';

@Component({
  selector: 'jhi-config-update',
  templateUrl: './config-update.component.html'
})
export class ConfigUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    maxMise: [],
    maxGagnant: [],
    urlServer: [],
    apiKey: [],
    apiPass: [],
    montantMise: [],
    gains1: [],
    gains2: [],
    gains3: [],
    gains4: [],
    gains5: [],
    gains6: [],
    gains7: [],
    gains8: []
  });

  constructor(protected configService: ConfigService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ config }) => {
      this.updateForm(config);
    });
  }

  updateForm(config: IConfig) {
    this.editForm.patchValue({
      id: config.id,
      maxMise: config.maxMise,
      maxGagnant: config.maxGagnant,
      urlServer: config.urlServer,
      apiKey: config.apiKey,
      apiPass: config.apiPass,
      montantMise: config.montantMise,
      gains1: config.gains1,
      gains2: config.gains2,
      gains3: config.gains3,
      gains4: config.gains4,
      gains5: config.gains5,
      gains6: config.gains6,
      gains7: config.gains7,
      gains8: config.gains8
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const config = this.createFromForm();
    if (config.id !== undefined) {
      this.subscribeToSaveResponse(this.configService.update(config));
    } else {
      this.subscribeToSaveResponse(this.configService.create(config));
    }
  }

  private createFromForm(): IConfig {
    return {
      ...new Config(),
      id: this.editForm.get(['id']).value,
      maxMise: this.editForm.get(['maxMise']).value,
      maxGagnant: this.editForm.get(['maxGagnant']).value,
      urlServer: this.editForm.get(['urlServer']).value,
      apiKey: this.editForm.get(['apiKey']).value,
      apiPass: this.editForm.get(['apiPass']).value,
      montantMise: this.editForm.get(['montantMise']).value,
      gains1: this.editForm.get(['gains1']).value,
      gains2: this.editForm.get(['gains2']).value,
      gains3: this.editForm.get(['gains3']).value,
      gains4: this.editForm.get(['gains4']).value,
      gains5: this.editForm.get(['gains5']).value,
      gains6: this.editForm.get(['gains6']).value,
      gains7: this.editForm.get(['gains7']).value,
      gains8: this.editForm.get(['gains8']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConfig>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
