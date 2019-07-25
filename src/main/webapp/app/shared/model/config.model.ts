export interface IConfig {
  id?: number;
  maxMise?: number;
  maxGagnant?: number;
  urlServer?: string;
  apiKey?: string;
  apiPass?: string;
  montantMise?: number;
  gains1?: number;
  gains2?: number;
  gains3?: number;
  gains4?: number;
  gains5?: number;
  gains6?: number;
  gains7?: number;
  gains8?: number;
}

export class Config implements IConfig {
  constructor(
    public id?: number,
    public maxMise?: number,
    public maxGagnant?: number,
    public urlServer?: string,
    public apiKey?: string,
    public apiPass?: string,
    public montantMise?: number,
    public gains1?: number,
    public gains2?: number,
    public gains3?: number,
    public gains4?: number,
    public gains5?: number,
    public gains6?: number,
    public gains7?: number,
    public gains8?: number
  ) {}
}
