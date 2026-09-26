package com.kisaan.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>🌱 KISAAN.AI — Backend REST API</title>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
                        background: #F7F2E9;
                        color: #6B4423;
                        margin: 0;
                        padding: 40px 20px;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        min-height: 85vh;
                    }
                    .card {
                        background: #ffffff;
                        max-width: 680px;
                        width: 100%;
                        border-radius: 16px;
                        box-shadow: 0 10px 30px rgba(107, 68, 35, 0.12);
                        padding: 36px;
                        border: 1px solid #E5DCCB;
                        text-align: center;
                    }
                    .badge {
                        display: inline-block;
                        background: #E8F5E9;
                        color: #2E7D32;
                        padding: 6px 14px;
                        border-radius: 20px;
                        font-size: 13px;
                        font-weight: 700;
                        margin-bottom: 16px;
                        border: 1px solid #A5D6A7;
                    }
                    h1 {
                        font-size: 32px;
                        margin: 0 0 10px;
                        color: #4C7A3D;
                    }
                    p {
                        color: #555;
                        font-size: 15px;
                        line-height: 1.6;
                        margin: 0 0 24px;
                    }
                    .cta-btn {
                        display: inline-block;
                        background: #4C7A3D;
                        color: #ffffff;
                        padding: 14px 28px;
                        border-radius: 10px;
                        text-decoration: none;
                        font-weight: bold;
                        font-size: 16px;
                        box-shadow: 0 4px 14px rgba(76, 122, 61, 0.35);
                        transition: all 0.2s ease;
                        margin-bottom: 24px;
                    }
                    .cta-btn:hover {
                        background: #3B5F30;
                        transform: translateY(-1px);
                    }
                    .grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
                        gap: 12px;
                        margin: 24px 0;
                        text-align: left;
                    }
                    .grid a {
                        display: block;
                        background: #FAF7F2;
                        border: 1px solid #E8E0D2;
                        padding: 12px 14px;
                        border-radius: 8px;
                        text-decoration: none;
                        color: #6B4423;
                        font-size: 13px;
                        font-weight: 600;
                    }
                    .grid a:hover {
                        border-color: #4C7A3D;
                        background: #F1F8ED;
                    }
                    .footer {
                        border-top: 1px solid #EFEAE0;
                        margin-top: 24px;
                        padding-top: 18px;
                        font-size: 13px;
                        color: #888;
                    }
                </style>
            </head>
            <body>
                <div class="card">
                    <span class="badge">🟢 Backend API Active • Port 8085</span>
                    <h1>🌱 KISAAN.AI</h1>
                    <p>
                        You have reached the <strong>Spring Boot REST API</strong> server.<br>
                        The interactive web application user interface runs on <strong>Port 5173</strong>.
                    </p>

                    <div>
                        <a href="http://localhost:5173" class="cta-btn">
                            🚀 Open KISAAN.AI Web App (localhost:5173) &rarr;
                        </a>
                    </div>

                    <div style="font-weight: 700; font-size: 14px; color: #6B4423; margin-top: 10px; text-align: left;">
                        Quick API & Admin Links:
                    </div>
                    <div class="grid">
                        <a href="/api/health" target="_blank">🩺 /api/health</a>
                        <a href="/api/schemes" target="_blank">🏛️ /api/schemes (30)</a>
                        <a href="/api/alerts" target="_blank">⚠️ /api/alerts (27+)</a>
                        <a href="/h2-console" target="_blank">🗄️ H2 Console (kisaandb)</a>
                    </div>

                    <div class="footer">
                        KISAAN.AI &bull; AI-Powered Digital Agriculture Platform for Indian Farmers
                    </div>
                </div>
            </body>
            </html>
            """;
    }
}
