//입출고 등록 유효성 검사
function registArtist() {
        const form = document.artistForm;
        const artist_id = form.artist_id.value;
        const artist_name = form.artist_name.value;
        const year = form.year.value;
        const month = form.month.value;
        const day = form.day.value;
        const artist_gender = form.artist_gender.value;
        const talent = form.talent.value;
        const agency = form.agency.value;

        if (artist_id === "") {
            alert("참가번호가 입력되지 않았습니다");
            form.artist_id.focus();
            return;
        }
        if (artist_name === "") {
            alert("참가자명이 입력되지 않았습니다");
            form.artist_name.focus();
            return;
        }
        if (year === "" || month === "" || day === "") {
            alert("생년월일이 입력되지 않았습니다");
            form.year.focus();
            return;
        }
        if (artist_gender === "") {
            alert("성별이 선택되지 않았습니다");
            form.artist_gender.focus();
            return;
        }
        if (talent === "") {
            alert("특기가 선택되지 않았습니다");
            form.talent.focus();
            return;
        }
        if (agency === "") {
            alert("소속사가 입력되지 않았습니다");
            form.agency.focus();
            return;
        }
        alert("오디션 지원자 정보가 등록되었습니다.");
        form.submit();
    }
//입출고 등록 다시쓰기
function resetArtist(){
	const form = document.artistForm;
	window.alert("정보를 지우고 처음부터 다시 입력 합니다.")
	artistForm.reset();
	artistForm.artist_id.focus();
}