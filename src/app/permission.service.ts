import { Injectable } from "@angular/core";

@Injectable({
    providedIn:'root'
})
export class PermissionService{

    private authTokenKey='authToken';
    private modulePermissionKey='modulePermissions';
    private roleIdKey='role_Id';
    private roleNamekey='role_Name';

    constructor(){}

    setAuthToken(token:string):void{
        localStorage.setItem(this.authTokenKey,token);
    }

    getAuthToken():string | null{
        return localStorage.getItem(this.authTokenKey);
    }

    setModulePermissions(permissions:any):void{
        localStorage.setItem(this.modulePermissionKey,JSON.stringify(permissions));
    }

    getModulePermissions():any[]{
        const modulePermission = localStorage.getItem(this.modulePermissionKey);
        return modulePermission ? JSON.parse(modulePermission) : [];
    }

    setRoleId(roleId:string):void{
        localStorage.setItem(this.roleIdKey,roleId);
    }

    getRoleId():string|null{
        return localStorage.getItem(this.roleIdKey);
    }

    setRoleName(roleName:string):void{
        localStorage.setItem(this.roleNamekey,roleName);
    }

    getRoleName():string|null{
        return localStorage.getItem(this.roleNamekey);
    }

    clearPermissions():void{
        localStorage.removeItem(this.authTokenKey);
        localStorage.removeItem(this.modulePermissionKey);
        localStorage.removeItem(this.roleIdKey);
        localStorage.removeItem(this.roleNamekey);
    }

    hasPermission(moduleName:string,permissionType:string):boolean{
        const permissions=this.getModulePermissions();

        
        if(this.getRoleId()=='1' && this.getRoleName()=='ROLE_ADMIN'){
            return true;
        }

        if(permissions){
            const module=permissions.find((m:any)=>m.moduleName===moduleName);
            if(module){
                return module[permissionType];
            }
        }
        return false;
    }
}