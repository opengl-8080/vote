var MarkdownUtil = (function() {
    marked.setOptions({
        langPrefix: ''
    });

    function MarkdownUtil() {
        this.render = function(options) {
            var sourceElement = document.querySelector(options.src);
            var sourceMarkdown;

            if (sourceElement.tagName === 'TEXTAREA') {
                sourceMarkdown = sourceElement.value;
            } else {
                sourceMarkdown = sourceElement.textContent;
            }

            var parsedMarkdown = marked(sourceMarkdown);
            document.querySelector(options.to).innerHTML = parsedMarkdown;

            var codeBlocks = document.querySelectorAll(options.to + ' pre code');

            for (var i=0; i<codeBlocks.length; i++) {
                var codeBlock = codeBlocks[i];
                hljs.highlightBlock(codeBlock);
            }
        };
    }

    return new MarkdownUtil();
})();