import { AbstractControl } from '@angular/forms';

export class PasswordValidation {


  static MatchPassword(AC: AbstractControl): any {
    let password = AC.get('password').value;
    let confirmarPassword = AC.get('confirmarPassword').value;
    if (password !== confirmarPassword && confirmarPassword?.length > 0) {
      AC.get('confirmarPassword').setErrors({ MatchPassword: true })
    } else {
      return null
    }
  }
}
