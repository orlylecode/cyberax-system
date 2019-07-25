import { NgModule } from '@angular/core';

import { CyberaxSystemSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [CyberaxSystemSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [CyberaxSystemSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CyberaxSystemSharedCommonModule {}
