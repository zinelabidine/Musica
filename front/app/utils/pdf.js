(function () {
  "use strict";

  angular.module('app').factory(
    'pdfService',
      [
        '$http',
        '$log',
        'EnvironmentConfig',
        function ($http,$log,EnvironmentConfig) {
          var content = [];
          var commande = '';
          var style = {
              header: { fontSize: 16, bold: true, margin: [0,20,0,40]},
              infoheader: { fontSize: 12, bold: true, margin: [0,0,0,10]},
              information: { fontSize: 12, bold: false, margin: [10, 0, 10, 40]},
              tableInstrument: { fontSize: 12, bold: true, margin: [0, 5, 0, 15]},
              total: { margin: [0, 5, 0, 15]},
              tableHeader: { bold: true, fontSize: 13, color: 'black'}
          };

          function getHeader(data) {
            return {
                style: 'header',
                columns: [
                    {
                        width: 180,
                        text: EnvironmentConfig.AppName + '\n',
                        color: 'blue'
                    },
                    { width: '*',	text: ''},{width: '*',text: ''},
                    {
                        width: 180,
                        text: 'Commande N° ' + data.commandeId + ' \n'
                    }
                ]
            };
          }

          function getFactureInfoHeader() {
            // Start information header
            return {
                style: 'infoheader',
                columns: [
                    { width: 180, text: 'Emetteur: '},
                    { width: '*',	text: ''},{width: '*',text: ''},
                    { width: 180, text: 'Adressé à:'}
                ]
            } // End information header
          }
          function getFactureInfo(data,clientInfo) {
            return { // Start Facture information
                style : 'information',
                columns: [
                    {
                        width: 180,
                        text: [
                            EnvironmentConfig.AppName + '\n',
                            EnvironmentConfig.Address1 + '\n',
                            EnvironmentConfig.Address2 + '\n',
                            EnvironmentConfig.Tel + '\n'
                        ]
                    },
                    { width: '*',	text: ''},{width: '*',text: ''},
                    {
                        width: 160,
                        text: [
                            'Nom: '+ clientInfo.firstname +'\n',
                            'Adresse: '+ clientInfo.address +' \n',
                            'Téléphone: '+ clientInfo.tel +'\n'
                        ]
                    }
                ]
            }
          }

          function getFactureInstrumentContent(instruments) {
            var table = {
                style: 'tableInstrument',
                table: {
                    widths: ['*', 30, 60, 40, 120],
                    body: [
                        [ 'Désignation', 'Qté', 'P.U ' , 'Remise', 'TotaHT'],
                    ]
                }
            };
            var cpt = 1;
            var TVA = 17;
            instruments.forEach(function(instrument) {
              table.table.body[cpt] = [
                instrument.instrument.reference,
                instrument.quantite + '',
                Math.round(instrument.instrument.prix*100)/100 + '',
                Math.round(instrument.instrument.remise*100)/100 + '',
                Math.round(instrument.quantite*(instrument.instrument.prix-instrument.instrument.remise)*100)/100 + ''
                // (instrument.quantite*instrument.instrument.prix*TVA/100 + instrument.instrument.prix*instrument.quantite) + ''
              ];
              cpt++;
            });
            return table;
          }

          function getFactureTotalContent(commande) {
            return {
                style: 'total',
                columns: [
                    { width: '*',	text: ''},
                    {
                        width: 120,
                        text:  'Total H.T\nTotal T.V.A\nTotal T.T.C',
                    },
                    {
                        width: 120,
                        alignment: 'right',
                        text:
                          Math.round(commande.montantHT*100)/100 + '\n'
                          + Math.round((commande.montantTTC - commande.montantHT)*100)/100 +'\n'
                          + Math.round(commande.montantTTC*100)/100
                    }
                ]
            };
          }

          function createPdf(data,clientInfo) {
            // Set pdf header
            content[0] = getHeader(data);

            // Set facture information header
            content[1] = getFactureInfoHeader();

            // Set facture information
            content[2] = getFactureInfo(data,clientInfo);

            // Add instruments to facture
            content[3] = getFactureInstrumentContent(data.lignesCommande);

            // Set facture total
            content[4] = getFactureTotalContent(data);

            // Create pdf
            var docDefinition = {};
            docDefinition.styles = style;
            docDefinition.content = content;
            return pdfMake.createPdf(docDefinition);

          }

          return {
            getCommandeAsPdf: function(data,clientInfo) {
              return createPdf(data,clientInfo);
            }
          }
        }
      ]
  );
}());
