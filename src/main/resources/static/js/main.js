const dialog = document.getElementById("taskDialog");
const form = document.getElementById("taskForm");
const saveButton = document.getElementById("saveButton");
const maxLength = 200;
	
// メモの文字数をチェック
function checkMemoLength() {
    const length = document.getElementById("memo").value.length;
	const memoCount = document.getElementById("memoCount");

    memoCount.textContent = `${length} / ${maxLength}文字`;
	
    if (length > maxLength) {
       	memoCount.style.color = "red";
       	saveButton.disabled = true;
	} else {
	    memoCount.style.color = "";
	    saveButton.disabled = false;
	}
}

// タスク追加ボタン
document.getElementById("addButton").addEventListener("click", () => {
    form.reset();
    document.getElementById("id").value = -1;
	document.getElementById("memo").value = "";
    form.action = "/add";
	checkMemoLength();
	dialog.showModal();
});

// タスク編集ボタン
document.querySelectorAll(".editButton").forEach(button => {
    button.addEventListener("click", () => {
        // タスクの情報を取得
        document.getElementById("id").value = button.dataset.id;
        document.getElementById("title").value = button.dataset.title;
        document.getElementById("typeId").value = button.dataset.typeId;
        document.getElementById("deadline").value = button.dataset.deadline || "";
		document.getElementById("memo").value = button.dataset.memo || "";
		// 文字数を反映
		checkMemoLength();
		
        form.action = "/edit";
        dialog.showModal();
    });
});

// メモ入力中に文字数をチェック
memo.addEventListener("input", checkMemoLength);

// メモクリアボタン
document.getElementById("clearMemo").addEventListener("click", () => {
    document.getElementById("memo").value = "";
    checkMemoLength();
});

// メモ表示ボタン
document.querySelectorAll(".memoButton").forEach(button => {
   	button.addEventListener("click", () => {
		// タスクの情報を取得
		document.getElementById("memoTitle").textContent = button.dataset.title + " のメモ";
		document.getElementById("memoText").textContent = button.dataset.memo || "（メモ内容なし）";
		
		document.getElementById("memoDialog").showModal();
	});
});
