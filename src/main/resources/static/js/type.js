const dialog = document.getElementById("typeDialog");
const form = document.getElementById("typeForm");

// 種別編集ボタン
document.querySelectorAll(".editType").forEach(button => {
    button.addEventListener("click", () => {
        document.getElementById("id").value = button.dataset.id;
        document.getElementById("name").value = button.dataset.name;
        form.action = "/editType";
        dialog.showModal();
    });
});