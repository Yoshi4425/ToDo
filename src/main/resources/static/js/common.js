// 共通：ダイアログを閉じる
document.querySelectorAll(".closeDialog").forEach(button => {
    button.addEventListener("click", () => {
        const dialog = button.closest("dialog");
        if (dialog) {
            dialog.close();
        }
    });
});

// 共通：エラーダイアログが存在する場合に表示する
const errorDialog = document.getElementById("errorDialog");
if (errorDialog) {
    errorDialog.showModal();
}
