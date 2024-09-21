import { Component, OnInit } from '@angular/core';
import { CajeroService } from '../../service/usuario.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export default class HomeComponent implements OnInit {
  cajeros: any[]=[];
  cajero = {
    id: 0,
    documento: '',
    nombre: '',
    domicilio: '',
    telefono: '',
    email: '',
    estado: ''
  };
  errorMessage = '';
  successMessage = '';
  openmodal = false;
  constructor(private cajeroService: CajeroService) {}
  ngOnInit(): void {
    this.getCajeros();
  }
  OpenModalButton(){
    this.openmodal = true;
  }
  CloseModalButton(){
    this.openmodal = false;
  }
  getCajeros(): void {
    this.cajeroService.getCajeros().subscribe( (res)=>{
      this.cajeros = res;
    }
    );
  }

  registrarCajero(): void {
    this.cajeroService.createCajero(this.cajero).subscribe((res)=>{
      this.getCajeros(); 
      this.cajero.id = 0;
    }
    );
  }

  modificarCajero(): void {
    this.cajeroService.updateCajero(this.cajero).subscribe(
      response => {
        this.successMessage = 'Cajero modificado con éxito';
        this.getCajeros(); 
      },
      error => this.errorMessage = 'Error al modificar el cajero'
    );
  }

  darDeBaja(id: number): void {
    this.cajeroService.Bajar(id).subscribe((res)=>{
      this.getCajeros();
    }
    );
  }

  restaurarCajero(id: number): void {
    this.cajeroService.Restaurar(id).subscribe((res)=>{
      this.getCajeros();
    }
    );
  }
  Editar(cajero: any){
    this.openmodal = true;
    this.cajero = cajero;
  }
}
