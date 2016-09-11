(function() {
    var summaryTextArea = document.querySelector('.summaryTextArea');
    var switchPreview = document.getElementById('switchPreview');
    var preview = document.getElementById('preview');

    var isPreviewMode = false;

    preview.style.display = 'none';

    switchPreview.addEventListener('click', function() {
        isPreviewMode = !isPreviewMode;

        if (isPreviewMode) {
            MarkdownUtil.render({
                src: '.summaryTextArea',
                to: '#preview'
            });

            preview.style.display = 'block';
            summaryTextArea.style.display = 'none';
            switchPreview.textContent = '編集';
        } else {
            preview.style.display = 'none';
            summaryTextArea.style.display = 'block';
            switchPreview.textContent = 'プレビュー';
        }
    });
})();
