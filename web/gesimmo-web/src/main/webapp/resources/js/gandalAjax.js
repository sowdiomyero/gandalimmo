$(document).ready(function() {    
    
    $('#table_utilisateurs').on('click', '.showUser', function(e) {
        e.preventDefault();
        $('#userEditSuccess').css('display', 'none');
        $('#userEditFailed').css('display', 'none');
        var idUser = $(this).attr('data-id');
        $('#viewUserModal').modal();

       // routeController.user.getUserById(idUser, populateUserModalForm);      
        routeController.user.getUserById(idUser, function(ajaxResponse){
           $('#editNomInput').val(ajaxResponse.nom);
           $('#editPrenomInput').val(ajaxResponse.prenom);
           $('#editEmailInput').val(ajaxResponse.email);
           $('#editTelInput').val(ajaxResponse.telephone);
           $('#editLoginInput').val(ajaxResponse.login);
           $('#editIdUserInput').val(ajaxResponse.idUser);
           
        });

    });
    
    $('#table_utilisateurs').on('click', '.deleteUser', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteConfirmationModal').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeletionModalButton', function() {
                    $('#deleteConfirmationModal').hide();
                    routeController.user.deleteUserById(id);                    
         });
    });
    
    $('#table_utilisateurs').on('click', '.bloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        routeController.user.blockUserById(idUser);
    });
    
    $('#updateUserForm').on('click', '.debloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        routeController.user.unblockUserById(idUser);
    });


    //Bloquer un utilisateur

    $('#table_utilisateurs').on('click', '.debloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        routeController.user.unblockUserById(idUser);
    });

 $('#table_roles').on('click', '.showRole', function(e) {
        e.preventDefault();
        $('#roleEditSuccess').css('display', 'none');
        $('#roleEditFailed').css('display', 'none');
        var idRole = $(this).attr('data-id');
        $('#viewRoleModal').modal();
        routeController.role.view(idRole, function(response){
             $('#editDescRole').val(response.roleDesc);
             $('#editNomRole').val(response.nameRole);
             $('#editIdRole').val(response.idRole);
        });


    });
      $('#table_roles').on('click', '.deleteRole', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteRoleConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteRoleConfirmation', function() {
                    $('#deleteRoleConfirmation').hide();
                    routeController.role.delete(id); 
                });
    });
 
 
  $('.showProfil').click(function(e) {
        e.preventDefault();
        $('#profilMessageSuccess').css('display', 'none');
        $('#profilMessageFailed').css('display', 'none');
        var login = $(this).attr('data-id');
        routeController.user.viewProfil(login, function(response){
            $('#viewProfilModal').modal();
                $('#editProfilNomInput').val(response.nom);
                $('#editProfilPrenomInput').val(response.prenom);
                $('#editProfilEmailInput').val(response.email);
                $('#editProfilTelInput').val(response.telephone);
                $('#editProfilLoginInput').val(response.login);
        });
       
    });

//$('#editlocalisationModalForm').submit(function(e) {
    $(document).on('submit','#editlocalisationModalForm',function(e){
        var frm = $('#editlocalisationModalForm');
        e.preventDefault();
        var data = {};
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        //alert("submit reçu !!");
        routeController.localisation.update(data, frm);
        
    });
    
    $(document).on('submit','#addLocalisationModalForm',function(e){
        var frm = $('#addLocalisationModalForm');
        e.preventDefault();
        var data = {};
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        routeController.localisation.update(data, frm);
        
    });
    
    $(document).on('submit','#addNiveauModalForm',function(e){
        var frm = $('#addNiveauModalForm');
        e.preventDefault();
        var data = {};
        var tabs = $("#addNiveauModalForm input:checked");
        $.each(this, function(i, v) {
            var input = $(v);           
            data[input.attr("name")] = input.val();
            delete data["undefined"];
            delete data["wifi"];
            delete data["camera"];
            delete data["ascensseur"];
            delete data["extincteur"];            
        });
        $.each(tabs, function(i, v) {
           var inp = $(v);   
           data[inp.attr("name")] = true;
        });
        
        
        routeController.niveau.addNew(data, frm);
        
    });

});

