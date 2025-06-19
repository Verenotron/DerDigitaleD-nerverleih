export interface IFronendNachrichtEvent{

    typ: 'DOENER' | 'ZUTAT' | 'BENUTZER';
    id: number;
    action: 'CREATE' | 'UPDATE' | 'DELETE';

}