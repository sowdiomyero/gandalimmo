var app = angular.module('Gesimmo', ['ngAnimate']);
app.controller('batimentController', ['$scope', '$location','batimentService', function($scope, $location, batimentService){
   $scope.campagnes = [
                        {name: 'Mermoz Pyrotechnique', desc:"Dakar Plateau"},
                        {name: 'Point E', desc:"Medina Rue 22"},
                        {name: 'Sicap Foire', desc:"Liberté 6"},
                        {name: 'Ngor-Almadies', desc:"Sacrée Coeur"}
                    ]; 
                    
    $scope.tasks = ["Schedule meeting with new client",
                    "Create email address for new intern",
                    " Have IT fix the network printer",
                    "Copy backups to offsite location",
                    "Food truck fixie locavors mcsweeney",
                    "Create email address for new intern",
                    "Have IT fix the network printer"
                   ];  
                   
    $scope.niveaux = [
        {nom : '1er Ahmadou Bamba', superficie:'25m²', etat:'EXCELLENT', etage:'1er Etage', caracteristique :[
           "Wifi", "Camera", "Ascenseur", "Balcon"     
        ]},
        {nom : '2eme Bleu', superficie:'123m²', etat:'TRES BON', etage:'2eme Etage', caracteristique :[
               "Wifi", "Camera", "Ascenseur", "Balcon"     
            ]},
        {nom : 'RDC Tivaouane', superficie:'187m²', etat:'BON', etage:'RDC', caracteristique :[
               "Wifi", "Camera", "Ascenseur", "Balcon"     
            ]},
        {nom : '18eme Tour Eiffel', superficie:'100m²', etat:'TRES BON', etage:'18eme Etage', caracteristique :[
               "Wifi", "Camera", "Ascenseur", "Balcon"     
            ]}
    ];
    
    $scope.default ="niveau";
        
    $scope.reloadData = function(){
        batimentService.getBatimentsNiveaux(2);
    };
}]);

app.factory('batimentService', ['$http',function($http){
   var batimentNiveaux = "/atiment/1/niveaux";
   return {
     getBatimentsNiveaux: function(id){
         $http.get("http://localhost:8080/gesimmo/browse/batiment/"+id+"/niveaux")
                    .success(function(data) {
                           alert(data.status);
                   })
                   .error(function(data) {
                           alert(data);
                   });
     }  
   };
}]);


