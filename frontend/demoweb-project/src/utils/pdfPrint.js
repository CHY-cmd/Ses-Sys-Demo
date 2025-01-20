
export async function printPdfFromBlob(blob) {

    // 创建一个隐藏的 <iframe> 元素来加载 PDF 文件
    const iframe = document.createElement('iframe');
    iframe.style.display = 'none';
    iframe.src = URL.createObjectURL(blob);

    // 添加到 DOM
    document.body.appendChild(iframe);

    // 等待 PDF 加载完成并调用打印对话框
    iframe.onload = () => {
        iframe.contentWindow.focus();
        iframe.contentWindow.print();
    };
  }