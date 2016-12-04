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
                    { width: '*',	text: '.'},{width: '*',text: '.'},
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
                    { width: '*',	text: '.'},{width: '*',text: '.'},
                    { width: 180, text: 'Adressé à:'}
                ]
            } // End information header
          }
          function getFactureInfo(data) {
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
                    { width: '*',	text: '.'},{width: '*',text: '.'},
                    {
                        width: 160,
                        text: [
                            'Nom: \n',
                            'Adresse: \n',
                            'Téléphone: \n'
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
                        [ 'Désignation', 'Qté', 'P.U HT', 'TVA', 'Total HT'],
                    ]
                }
            };
            var cpt = 1;
            instruments.forEach(function(instrument) {
              table.table.body[cpt] = [
                instrument.instrument.reference,
                instrument.quantite + '',
                instrument.instrument.prix + '',
                '17%',
                instrument.quantite*instrument.instrument.prix + ''
              ];
              cpt++;
            });
            return table;
          }

          function getFactureTotalContent(commande) {
            return {
                style: 'total',
                columns: [
                    { width: '*',	text: '.'},
                    {
                        width: 120,
                        text:  'Total H.T\nTotal T.V.A\nTotal T.T.C',
                    },
                    {
                        width: 120,
                        alignment: 'right',
                        text:
                          commande.montantHT + '\n'
                          + (commande.montantTTC - commande.montantHT) +'\n'
                          + commande.montantTTC
                    }
                ]
            };
          }

          function createPdf(data) {
            // Set pdf header
            content[0] = getHeader(data);

            // Set facture information header
            content[1] = getFactureInfoHeader();

            // Set facture information
            content[2] = getFactureInfo(data);

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
            getCommandeAsPdf: function(data) {
              return createPdf(data);
            }
          }
        }
      ]
  );
}());
