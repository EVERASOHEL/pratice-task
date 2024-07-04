import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { PermissionService } from "../permission.service";

@Injectable({
    providedIn:'root'
})
export class RoleGruard implements CanActivate{

    constructor(private router:Router,private permissionService:PermissionService){}

    canActivate(
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot): 
        boolean|Observable<boolean>|Promise<boolean> {
        
        const requiredRole=route.data['requiredRole'];
        const requiredPermission=route.data['requiredPermission'];
        const roleId=this.permissionService.getRoleId();
        const roleName=this.permissionService.getRoleName();
        
        if(roleId=='1' && roleName=="ROLE_ADMIN"){
            return true;
        }else if(roleId && roleId===requiredRole){
            return true;
        }else if(this.permissionService.hasPermission('product',requiredPermission)){
            return true;
        }else{
            return false;
        }
    }
    
}