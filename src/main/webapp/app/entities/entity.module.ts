import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'joueur',
        loadChildren: './joueur/joueur.module#CyberaxSystemJoueurModule'
      },
      {
        path: 'mise',
        loadChildren: './mise/mise.module#CyberaxSystemMiseModule'
      },
      {
        path: 'jeu',
        loadChildren: './jeu/jeu.module#CyberaxSystemJeuModule'
      },
      {
        path: 'gagnant',
        loadChildren: './gagnant/gagnant.module#CyberaxSystemGagnantModule'
      },
      {
        path: 'list-attente',
        loadChildren: './list-attente/list-attente.module#CyberaxSystemListAttenteModule'
      },
      {
        path: 'terminal',
        loadChildren: './terminal/terminal.module#CyberaxSystemTerminalModule'
      },
      {
        path: 'config',
        loadChildren: './config/config.module#CyberaxSystemConfigModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CyberaxSystemEntityModule {}
