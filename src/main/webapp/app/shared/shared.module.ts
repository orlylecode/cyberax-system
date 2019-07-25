import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CyberaxSystemSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [CyberaxSystemSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [CyberaxSystemSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CyberaxSystemSharedModule {
  static forRoot() {
    return {
      ngModule: CyberaxSystemSharedModule
    };
  }
}
