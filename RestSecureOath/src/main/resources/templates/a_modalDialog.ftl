<div class='ng-modal' ng-show='show'>
  <div class='ng-modal-overlay' ng-click='hideModal()'></div>
  <div class='ng-modal-dialog' ng-style='dialogStyle'>
    <div id='close-modal' class='ng-modal-close' ng-click='hideModal()'>&times;</div>
    <div class='ng-modal-dialog-content' ng-transclude></div>
  </div>
</div>