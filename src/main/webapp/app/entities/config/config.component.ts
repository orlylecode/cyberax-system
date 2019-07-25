import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConfig } from 'app/shared/model/config.model';
import { AccountService } from 'app/core';
import { ConfigService } from './config.service';

@Component({
  selector: 'jhi-config',
  templateUrl: './config.component.html'
})
export class ConfigComponent implements OnInit, OnDestroy {
  configs: IConfig[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected configService: ConfigService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.configService
      .query()
      .pipe(
        filter((res: HttpResponse<IConfig[]>) => res.ok),
        map((res: HttpResponse<IConfig[]>) => res.body)
      )
      .subscribe(
        (res: IConfig[]) => {
          this.configs = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInConfigs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IConfig) {
    return item.id;
  }

  registerChangeInConfigs() {
    this.eventSubscriber = this.eventManager.subscribe('configListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
